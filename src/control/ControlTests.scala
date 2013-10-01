package control

object PrintDirectoryFiles extends App {
    val filesHere = new java.io.File(".").listFiles

    for(file <- filesHere) println(file)
}