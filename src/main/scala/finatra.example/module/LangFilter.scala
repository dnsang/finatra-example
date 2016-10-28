package finatra.example.module

import javax.inject.Singleton

import com.twitter.finagle.http.{Request, RequestProxy, Response}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future

/**
 * Created by zkidkid on 10/27/16.
 */

object LangFilter {
  val EN = "en"
  val VI = "vi"
}

@Singleton
class LangFilter extends SimpleFilter[Request, Response] {


  override def apply(originRequest: Request, service: Service[Request, Response]): Future[Response] = {

    val destRequest = if (originRequest.path.startsWith(s"/${LangFilter.EN}/")) {
      new RequestProxy {
        this.headerMap.put("lang", LangFilter.EN)

        override def request: Request = originRequest

        override def uri: String = originRequest.path.substring(s"/${LangFilter.EN}".length)
      }
    } else {
      originRequest.headerMap.put("lang", LangFilter.VI)
      originRequest
    }
    service.apply(destRequest)
  }
}


