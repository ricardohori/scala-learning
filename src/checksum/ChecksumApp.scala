package checksum

import ChecksumAccumulator.calculate

object ChecksumApp extends App {
    for (season <- List("fall", "winter", "spring"))
        println(season + ": " + calculate(season))
}
