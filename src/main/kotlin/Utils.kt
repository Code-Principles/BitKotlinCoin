import java.security.MessageDigest

object Utils {

    fun sha256(input: Any): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
                .getInstance("SHA-256")
                .digest(input.hashCode().toString().toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

    fun MutableList<Block>.addNewBlock(data: String? = null) {
        this.add(Block(size,
                last().hash(),
                System.currentTimeMillis() / 1000,
                data))
    }

}