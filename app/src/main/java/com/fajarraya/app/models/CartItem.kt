data class CartItem(
    val kodeBarang: String,
    var quantity: Int,
    val gambar: String,
    val harga:Long,
    val nama:String,
)

fun Map<String, Any>.toCartItem(): CartItem {
    return CartItem(
        kodeBarang = this["kodeBarang"] as String,
        quantity = (this["quantity"] as Long).toInt(),
        gambar = this["gambar"] as String,
        harga = this["harga"] as Long,
        nama = this["nama"] as String,
    )
}