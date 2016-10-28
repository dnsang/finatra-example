package finatra.example.controller.http

import javax.inject.Singleton

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
 * Created by zkidkid on 10/27/16.
 */
@Singleton
class MultiLangRequest extends Controller {

  get("/hello") {
    req: Request => {
      response.ok(Map("lang" -> req.headerMap.get("lang")))
    }
  }
}
