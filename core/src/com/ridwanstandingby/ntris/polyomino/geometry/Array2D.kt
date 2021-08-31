package com.ridwanstandingby.ntris.polyomino.geometry

class Array2D<T>(val array: Array<Array<T>>) {

    companion object {

        inline operator fun <reified T> invoke() = Array2D(Array(0) { emptyArray<T>() })

        inline operator fun <reified T> invoke(xWidth: Int, yWidth: Int) =
            Array2D(Array(xWidth) { arrayOfNulls<T>(yWidth) })

        inline operator fun <reified T> invoke(
            xWidth: Int,
            yWidth: Int,
            operator: (Int, Int) -> (T)
        ): Array2D<T> {
            val array = Array(xWidth) { x ->
                Array(yWidth) { operator(x, it) }
            }
            return Array2D(array)
        }
    }

    operator fun get(x: Int, y: Int): T {
        return array[x][y]
    }

    operator fun get(v: IntVector2): T {
        return get(v.x, v.y)
    }

    operator fun set(x: Int, y: Int, t: T) {
        array[x][y] = t
    }

    operator fun set(v: IntVector2, t: T) {
        array[v.x][v.y] = t
    }

    inline fun forEach(operation: (T) -> Unit) {
        array.forEach { row -> row.forEach { operation.invoke(it) } }
    }

    inline fun forEachIndexed(operation: (x: Int, y: Int, T) -> Unit) {
        array.forEachIndexed { x, p -> p.forEachIndexed { y, t -> operation.invoke(x, y, t) } }
    }
}
