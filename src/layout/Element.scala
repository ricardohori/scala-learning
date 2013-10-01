package layout

import layout.Element.elem

/**
 * Created with IntelliJ IDEA.
 * User: rfh
 * Date: 9/30/13
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class Element {

    def contents: Array[String]

    def width = if (contents.size == 0) 0 else contents(0).length

    def height = contents.length

    def above(that: Element) = {
        val this1 = this widen that.width
        val that1 = that widen this.width
        elem(this1.contents ++ that1.contents)
    }

    def beside(that: Element) = {
        val this1 = this heighten that.height
        val that1 = that heighten this.height
        elem(
            for(
                (line1, line2) <- this1.contents zip that1.contents
            ) yield line1 + line2
        )
    }

    def widen(w: Int): Element =
        if(w <= width) this
        else{
            val left = elem(' ', (w - width)/2, height)
            val right = elem(' ', w - width - left.width, height)

            left beside this beside right
        }

    def heighten(h: Int) =
        if(h <= height) this
        else{
            val top = elem(' ', width, (h - height)/2)
            val bot = elem(' ', width, h - height - top.height)

            top above this above bot
        }

    override def toString = contents mkString "\n"

}

object Element {

    private class ArrayElement(val contents: Array[String]) extends Element

    private class LineElement(s:String) extends Element{
        val contents = Array(s)

        override val height = 1

        override val width = s.length
    }

    private class UniformElement(
        ch: Char,
        override val width: Int,
        override val height: Int
    ) extends Element {
        private val line = ch.toString * width

        val contents = Array.fill(height)(line)
    }

    def elem(contents: Array[String]): Element = new ArrayElement(contents)

    def elem(line: String): Element = new LineElement(line)

    def elem(ch: Char, width: Int, height: Int): Element = new UniformElement(ch, width, height)
}
