object BlockChain {
    private val genesisBlock = Block(
            0,
            "98010BD9270F9B100B6214A21754FD33BDC8D41B2BC9F9DD16FF54D3C34FFD71",
            1539028,
            "Genesis Block"
    )

    private val blockChain = mutableListOf<Block>().also { it.add(genesisBlock) }

    fun get(): List<Block> = blockChain

    fun addNewBlock(data: String? = null) {
        blockChain.add(Block(blockChain.size,
                blockChain.last().hash(),
                System.currentTimeMillis() / 1000,
                data))
    }

    fun isValid(chain: List<Block>): Boolean {
        for (index in chain.size - 1 downTo 0) {
            val block = chain[index]
            if (index == 0) {
                return block == genesisBlock
            } else {
                if (block.previousHash != chain[index - 1].hash()) {
                    return false
                }
            }
        }
        return true
    }
}