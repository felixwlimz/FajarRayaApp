package com.fajarraya.app.core.repository.products

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.fajarraya.app.core.data.local.datasource.ProductDataSource
import com.fajarraya.app.core.data.local.entity.ProductEntity
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.toFirebaseProduct
import com.fajarraya.app.core.utils.DataMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.util.UUID

class ProductRepository(
    private val productDataSource: ProductDataSource,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseFirestore: FirebaseFirestore,
    private val context: Context,
) : IProductRepository {

    private fun uploadImage(image: String): Single<String> {
        return Single.create { emit ->
            val storageReference = firebaseStorage.reference
            val fileReference = storageReference.child("products/${UUID.randomUUID()}.jpg")

            val inputStream = context.contentResolver.openInputStream(Uri.parse(image))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            val uploadTask = fileReference.putBytes(byteArrayOutputStream.toByteArray())
            uploadTask.addOnSuccessListener {
                fileReference.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()
                    emit.onSuccess(downloadUrl)
                }
            }.addOnFailureListener {
                emit.onError(Exception(it))
            }
        }
    }

    override fun getAllProducts(): Flowable<List<Products>> {
        return Flowable.create({ emit ->

            firebaseFirestore.collection("products")
                .get()

                .addOnSuccessListener {
                    val documents = it.documents.map { document ->
                        val firebaseProducts = document.toFirebaseProduct()
                        firebaseProducts.mapToProduct()
                    }.toList()
                    emit.onNext(documents)
                }

                .addOnFailureListener {
                    emit.onError(Exception(it))
                }


        }, BackpressureStrategy.LATEST)
    }

    override fun getProduct(kodeBarang: String): Flowable<Products> {
        return Flowable.create({ emitter ->
            firebaseFirestore
                .collection("products")
                .whereEqualTo("kodeBarang", kodeBarang)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        emitter.onNext(it.documents[0].toFirebaseProduct().mapToProduct())
                    } else {
                        emitter.onError(Exception("Error Querying Document"))
                    }
                }
                .addOnFailureListener {
                    emitter.onError(Exception(it))
                }
        }, BackpressureStrategy.LATEST)
    }

    override fun insertProduct(products: Products): Completable {
        return Completable.create { emitter ->
            uploadImage(products.gambarProduk)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        val firebaseProducts = products.mapProductToFirebaseProduct(it).apply {
                            this.gambarProduk = it
                        }
                        firebaseFirestore.collection("products")
                            .add(firebaseProducts)
                            .addOnSuccessListener {
                                emitter.onComplete()
                            }
                            .addOnFailureListener {
                                emitter.onError(Exception(it))
                            }
                    },
                    {
                        emitter.onError(Exception(it))
                    }
                )
        }
    }

    override fun deleteProduct(products: Products): Completable {
        return Completable.create { emitter ->
            firebaseFirestore
                .collection("products")
                .whereEqualTo("kodeBarang", products.kodeBarang)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        for (document in it.documents) {
                            firebaseFirestore.collection("products").document(document.id)
                                .delete()
                                .addOnFailureListener { e ->
                                    emitter.onError(Exception("Error Deleting"))
                                }
                        }
                        emitter.onComplete()
                    } else {
                        emitter.onError(Exception("Error Querying Document"))
                    }
                }
                .addOnFailureListener {
                    emitter.onError(Exception(it))
                }
        }
    }

    override fun updateProduct(products: Products): Completable {
        return Completable.create { emitter ->
            firebaseFirestore
                .collection("products")
                .whereEqualTo("kodeBarang", products.kodeBarang)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        val firebaseProducts = products.mapProductToFirebaseProduct()
                        it.documents[0].reference.set(firebaseProducts)
                            .addOnSuccessListener {
                                emitter.onComplete()
                            }
                            .addOnFailureListener {
                                emitter.onError(Exception(it))
                            }
                    } else {
                        emitter.onError(Exception("Error Querying Document"))
                    }
                }
                .addOnFailureListener {
                    emitter.onError(Exception(it))
                }
        }
    }


    private val mapperDomain = object : DataMapper<ProductEntity, Products> {
        override fun mapFrom(data: ProductEntity): Products {
            return Products(
                kodeBarang = data.kodeBarang,
                namaBarang = data.namaBarang,
                deskripsiProduk = data.deskripsiProduk,
                stok = data.stok,
                satuan = data.satuan,
                gambarProduk = data.gambarProduk.toString(),
                supplierId = data.supplierId,
                kategoriProduk = data.kategoriProduk,
                hargaProduk = data.hargaProduk
            )
        }

        override fun mapLists(data: List<ProductEntity>): List<Products> {
            return data.map { data ->
                Products(
                    kodeBarang = data.kodeBarang,
                    namaBarang = data.namaBarang,
                    deskripsiProduk = data.deskripsiProduk,
                    stok = data.stok,
                    satuan = data.satuan,
                    gambarProduk = data.gambarProduk.toString(),
                    supplierId = data.supplierId,
                    kategoriProduk = data.kategoriProduk,
                    hargaProduk = data.hargaProduk
                )
            }
        }

    }

    private val mapperEntity = object : DataMapper<Products, ProductEntity> {
        override fun mapFrom(data: Products): ProductEntity {
            return ProductEntity(
                kodeBarang = data.kodeBarang,
                namaBarang = data.namaBarang,
                deskripsiProduk = data.deskripsiProduk,
                stok = data.stok,
                satuan = data.satuan,
                gambarProduk = data.gambarProduk,
                supplierId = data.supplierId,
                kategoriProduk = data.kategoriProduk,
                hargaProduk = data.hargaProduk
            )
        }

        override fun mapLists(data: List<Products>): List<ProductEntity> {
            return data.map { data ->
                ProductEntity(
                    kodeBarang = data.kodeBarang,
                    namaBarang = data.namaBarang,
                    deskripsiProduk = data.deskripsiProduk,
                    stok = data.stok,
                    satuan = data.satuan,
                    gambarProduk = data.gambarProduk,
                    supplierId = data.supplierId,
                    kategoriProduk = data.kategoriProduk,
                    hargaProduk = data.hargaProduk
                )
            }
        }

    }

}