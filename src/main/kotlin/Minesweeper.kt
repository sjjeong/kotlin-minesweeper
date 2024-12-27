data class Minesweeper(
    val size: Size,
    val mineCount: Int,
    private val minePositionList: MutableList<Position> = mutableListOf(),
) {
    init {
        require(mineCount in 1 until size.height * size.width) {
            "지뢰의 개수는 높이와 너비의 곱 보다 작거나 같아야 합니다."
        }

        val diff = mineCount - minePositionList.size
        if (diff > 0) {
            val widthRange = (0 until size.width).shuffled().take(diff)
            val heightRange = (0 until size.height).shuffled().take(diff)
            for (i in 0 until diff) {
                minePositionList.add(Position(x = widthRange[i], y = heightRange[i]))
            }
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until size.height) {
            for (j in 0 until size.width) {
                val position = Position(j, i)
                if (minePositionList.contains(position)) {
                    stringBuilder.append("*")
                } else {
                    stringBuilder.append("C")
                }
                if (j == size.width - 1) {
                    stringBuilder.append("\n")
                } else {
                    stringBuilder.append(" ")
                }
            }
        }
        return stringBuilder.toString()
    }
}
