package forms

import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._

/**
 * Created by Henrik Kirk<henrik@busywait.org> on 4/2/16.
 */
object EventForm {
  val adminEventForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText
    )(AdminEventData.apply)(AdminEventData.unapply)
  )

  case class AdminEventData(id: Long, name: String)
}
