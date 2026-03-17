package services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.FeedbackCidadaoModel;

import static io.restassured.RestAssured.given;

public class CadastroFeedbackCidadaoService {
    final FeedbackCidadaoModel feedbackCidadaoModel = new FeedbackCidadaoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    public void setFieldsFeedbackCidadao(String field, String value) {
        switch (field) {
            case "id" -> feedbackCidadaoModel.setId(Integer.parseInt(value));
            case "autor" -> feedbackCidadaoModel.setAutor(value);
            case "tipo" -> feedbackCidadaoModel.setTipo(value);
            case "descricao" -> feedbackCidadaoModel.setDescricao(value);
            case "data" -> feedbackCidadaoModel.setData(value);
            case "status" -> feedbackCidadaoModel.setStatus(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void createFeedbackCidadao(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(feedbackCidadaoModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
