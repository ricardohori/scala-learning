package layout

import layout.Element.elem

/**
 * Created with IntelliJ IDEA.
 * User: rfh
 * Date: 10/1/13
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
object Spiral {
    val space = elem(" ")
    val corner = elem("+")

    def spiral(nEdges: Int, direction: Int): Element = {
        if(nEdges == 1) corner
        else{
            val sp = spiral(nEdges - 1, (direction + 3) % 4)
            def verticalBar = elem('|', 1, sp.height)
            def horizontalBar = elem('-', sp.width, 1)

            if (direction == 0)
                (corner beside horizontalBar) above (sp beside space)
            else if (direction == 1)
                (sp above space) beside (corner above verticalBar)
            else if (direction == 2)
                (space beside sp) above (horizontalBar beside corner)
            else
                (verticalBar above corner) beside (space above sp)
        }
    }

    def main(args: Array[String]){
        val nSides = args(0).toInt
        println(spiral(nSides, 0))
    }
}