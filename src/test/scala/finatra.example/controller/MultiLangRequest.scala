package finatra.example.controller

import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.thrift.ThriftClient
import com.twitter.inject.server.{EmbeddedTwitterServer, FeatureTest}
import finatra.example.Server
import com.twitter.finagle.http.Status
/**
  * Created by SangDang on 9/18/16.
  */
class MultiLangRequest extends FeatureTest{
  override protected def server = new EmbeddedHttpServer(twitterServer = new Server)

  "Default Request" in {
    server.httpGet(path = "/hello",andExpect = Status.Ok, withJsonBody =
      """
        {
          "lang":"vi"
        }
      """.stripMargin)

  }

  "EN Request" in {
    server.httpGet(path = "/en/hello",andExpect = Status.Ok, withJsonBody =
      """
        {
          "lang":"en"
        }
      """.stripMargin)

  }
}
