package ch1

sealed trait MapReduceMessage

case class WordCount(word: String, count: Int) extends MapReduceMessage

case class MapData(wordCounts: Array[WordCount]) extends MapReduceMessage

case class ReduceData(reducedData: Map[String,Int]) extends MapReduceMessage

case class Result() extends MapReduceMessage