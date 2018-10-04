data class Block(val index: Int,
                 val previousHash: String,
                 val timestamp: Long,
                 val data: String?) {
    fun hash(): String {
        return Utils.sha256(this)
    }
}