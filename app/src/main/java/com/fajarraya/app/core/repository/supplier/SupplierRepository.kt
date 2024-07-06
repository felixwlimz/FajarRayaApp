package com.fajarraya.app.core.repository.supplier

import com.fajarraya.app.core.data.local.datasource.SupplierDataSource
import com.fajarraya.app.core.data.local.entity.SupplierEntity
import com.fajarraya.app.core.data.local.entity.toSupplierEntity
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.model.toFirebaseProduct
import com.fajarraya.app.core.utils.DataMapper
import com.fajarraya.app.models.SortType
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.Flow

class SupplierRepository(
    private val firebaseFirestore: FirebaseFirestore,
    private val supplierDataSource: SupplierDataSource
) : ISupplierRepository {

    override fun getAllSuppliers(): Flowable<List<Suppliers>> {
        return Flowable.create({ emit ->

            val suppliersRef = firebaseFirestore.collection("suppliers")

            suppliersRef.addSnapshotListener { value, error ->
                val documents = value?.documents
                if (documents != null) {
                    val supplierList = documents.map { document ->
                        document.toSupplierEntity()
                    }.toList()
                    emit.onNext(mapperToDomain.mapLists(supplierList))
                }
            }
        }, BackpressureStrategy.LATEST)
    }

    override fun addSupplier(supplier: Suppliers): Completable {
        return Completable.create { emitter ->
            val firebaseSupplier = mapperToEntity.mapFrom(supplier)
            firebaseFirestore
                .collection("suppliers")
                .add(firebaseSupplier)
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(Exception(it))
                }
        }
    }


    override fun updateSupplier(supplier: Suppliers): Completable {
        return Completable.create { emitter ->
            firebaseFirestore
                .collection("suppliers")
                .whereEqualTo("supplierId", supplier.supplierId)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        it.documents[0].reference.set(mapperToEntity.mapFrom(supplier))
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

    override fun deleteSupplier(supplier: Suppliers): Completable {
        return Completable.create { emitter ->
            firebaseFirestore
                .collection("suppliers")
                .whereEqualTo("supplierId", supplier.supplierId)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        for (document in it.documents) {
                            firebaseFirestore.collection("suppliers").document(document.id)
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

    override fun getSupplier(supplierId: String): Flowable<Suppliers> {
        return Flowable.create({ emitter ->
            firebaseFirestore
                .collection("suppliers")
                .whereEqualTo("supplierId", supplierId)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        emitter.onNext(mapperToDomain.mapFrom(it.documents[0].toSupplierEntity()))
                    } else {
                        emitter.onError(Exception("Error Querying Document"))
                    }
                }
                .addOnFailureListener {
                    emitter.onError(Exception(it))
                }

        }, BackpressureStrategy.LATEST)
    }


    private val mapperToDomain = object : DataMapper<SupplierEntity, Suppliers> {
        override fun mapFrom(data: SupplierEntity): Suppliers {
            return Suppliers(
                supplierId = data.supplierId,
                supplierName = data.supplierName,
                supplierAddress = data.supplierAddress,
                phoneNumber = data.phoneNumber,
                city = data.city,
                province = data.province
            )
        }

        override fun mapLists(data: List<SupplierEntity>): List<Suppliers> {
            return data.map {
                Suppliers(
                    supplierId = it.supplierId,
                    supplierName = it.supplierName,
                    supplierAddress = it.supplierAddress,
                    phoneNumber = it.phoneNumber,
                    city = it.city,
                    province = it.province
                )
            }
        }

    }

    override fun sortSuppliers(sortType: SortType): Flowable<List<Suppliers>> {
        return Flowable.create({ emitter ->
            when (sortType) {
                SortType.ASCENDING -> {
                    firebaseFirestore.collection("suppliers")
                        .orderBy("name", Query.Direction.ASCENDING)
                        .get()
                        .addOnSuccessListener {
                            emitter.onComplete()
                        }
                        .addOnFailureListener {
                            emitter.onError(it)
                        }
                }

                SortType.DESCENDING -> {
                    firebaseFirestore.collection("suppliers")
                        .orderBy("name", Query.Direction.ASCENDING)
                        .get()
                        .addOnSuccessListener {
                            emitter.onComplete()
                        }
                        .addOnFailureListener {
                            emitter.onError(it)
                        }
                }
            }
        }, BackpressureStrategy.LATEST)
    }

    private val mapperToEntity = object : DataMapper<Suppliers, SupplierEntity> {
        override fun mapFrom(data: Suppliers): SupplierEntity {
            return SupplierEntity(
                supplierId = data.supplierId,
                supplierName = data.supplierName,
                supplierAddress = data.supplierAddress,
                phoneNumber = data.phoneNumber,
                city = data.city,
                province = data.province,
                description = data.description
            )
        }

        override fun mapLists(data: List<Suppliers>): List<SupplierEntity> {
            return data.map {
                SupplierEntity(
                    supplierId = it.supplierId,
                    supplierName = it.supplierName,
                    supplierAddress = it.supplierAddress,
                    phoneNumber = it.phoneNumber,
                    city = it.city,
                    province = it.province,
                    description = it.description
                )
            }
        }

    }
}