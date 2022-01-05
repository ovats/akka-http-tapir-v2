package com.demo

object WritingToFile {

  import java.time.LocalDateTime
  import java.io.{BufferedWriter, File, FileWriter}

  private var num             = 0
  private val fileNameDefault = "logmeplease.txt"

  def writeString(
      s: String,
      filename: String = fileNameDefault,
      newLine: Boolean = true,
      printTime: Boolean = true,
      indent: String = "=",
  ): Unit = {
    val file = new File(filename)
    val bw   = new BufferedWriter(new FileWriter(file, true))
    val dt   = if (printTime) s"${LocalDateTime.now()}:" else ""
    val nl   = if (newLine) "\n" else ""
    val ind  = " " * (num * 3)
    bw.write(s"$dt$s$nl")
    bw.close()
    indent match {
      case ">" => num += 1
      case "<" => num = if (num == 0) 0 else num - 1
    }
  }

  def writeList(
      list: List[String],
      filename: String = fileNameDefault,
      newLine: Boolean = true,
      printTime: Boolean = true,
  ): Unit = {
    val l = if (list.isEmpty) "[list is empty]" else list.mkString("[", "\n", "]")
    writeString(s = l, filename = filename, newLine = newLine, printTime = printTime)
  }

  def writeSep(filename: String = fileNameDefault, newLine: Boolean = true, printTime: Boolean = true): Unit = {
    writeString(s = "*" * 80, filename = filename, newLine = newLine, printTime = printTime)
  }

  def decInd = num = if (num == 0) 0 else num - 1
  def incInd = num += 1

}
