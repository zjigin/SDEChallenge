
trait Queue[T] {
  // O(1)
  def isEmpty: Boolean

  // O(1)
  def enQueue(t: T): Queue[T]

  // O(1)
  def head: Option[T]

  // O(1) amortised
  def deQueue: Queue[T]
}

/* Implementation based on the book
    Purely Functional Data Structures
    By Chris Okasaki
*/
class QueueImpl[T](in: List[T] = Nil, out: List[T] = Nil) extends Queue[T] {

  // O(1)
  override def isEmpty: Boolean = (in, out) match {
    case (Nil, Nil) => true
    case _ => false
  }

  // O(1)
  override def enQueue(t: T): Queue[T] = new QueueImpl[T](t :: in, out)

  // O(1)
  override def head: Option[T] = dequeue match {
    case (hd, _) => hd
  }

  // O(1) amortized
  // Returns a tuple of (head, tail)
  private def deQueue: (Option[T], Queue[T]) = out match {
    case hd :: tl => (Some(hd), new QueueImpl(in, tl))
    case Nil =>
      // In case the out list is empty; reverse the in and transfer it to out list to conserve FIFO
      in.reverse match {
        case hd :: tl => (Some(hd), new QueueImpl(Nil, tl))
        case Nil => (None, new QueueImpl()) // Empty queue
      }
  }

  // Utility method for tests
  def toList: List[T] = {
    out ::: in.reverse
  }
}

object Queue {
  def empty[T]: Queue[T] = new QueueImpl[T]()

  def apply[T](): Queue[T] = empty
}

object QueueTest extends App {

  val q1 = new QueueImpl[Int]()
  println(s"Empty: ${q1.isEmpty}", true)

  val q2 = q1.insert(1)
  println(s"Empty: ${q2.isEmpty}", false)
  println(s"Head: ${q2.head}", 1)

  val q3 = q2.insert(2).insert(3).insert(4)
  println(s"Tail: ${q3.tail.toList}", List(2, 3, 4))

  // Validate structure of the queue as a list
  println(q3.toList, List(1, 2, 3, 4))
}
