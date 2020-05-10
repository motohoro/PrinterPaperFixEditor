import javax.swing.JFrame

fun main(){
    println("HELLO")

    val jfm = JFrame("HHH")
    jfm.contentPane = MyForm2().panel1
    jfm.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    jfm.pack()
    jfm.isVisible = true

}