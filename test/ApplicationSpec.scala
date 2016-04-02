import controllers.HomeController
import org.scalatestplus.play._
import play.api.i18n.MessagesApi
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController" should {

    "should require a name" in {
      val messagesApi = app.injector.instanceOf[MessagesApi]
      val homeController = new HomeController(messagesApi)

      val path = controllers.routes.HomeController.saveDetails().toString
      val request = FakeRequest(POST, path)
        .withSession("csrfToken" -> "FakeCSRFToken")
        .withHeaders("X-Requested-With" -> "XMLHttpRequest", "Cookie" -> "csrfToken=FakeCSRFToken", "Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8")
        .withFormUrlEncodedBody(
          "csrfToken" -> "FakeCSRFToken",
          "id" -> "-1",
          "name" -> "")

      val result = homeController.saveDetails()(request)

      status(result) mustBe BAD_REQUEST
    }

  }

  "CountController" should {

    "return an increasing count" in {
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
    }

  }

}
