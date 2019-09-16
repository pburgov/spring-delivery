package com.pburgov.springdelivery.dao.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pburgov.springdelivery.dao.entity.Reparto;
import com.pburgov.springdelivery.dao.entity.RepartoDetalle;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class RepartoSerializer implements JsonSerializer <Reparto> {

    private final SimpleDateFormat repartoDf = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat descargaDf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    @Override
    public JsonElement serialize( Reparto reparto, Type type, JsonSerializationContext jsc ) {

        TimeZone timeZone = TimeZone.getTimeZone("Europe/Madrid");
        repartoDf.setTimeZone(timeZone);
        descargaDf.setTimeZone(timeZone);
        JsonArray finalArray = new JsonArray();

        JsonObject finalObject = new JsonObject();
        JsonObject repartoJsonObject = new JsonObject();

        repartoJsonObject.addProperty("id", reparto.getId());
        repartoJsonObject.addProperty("usuario", reparto.getDriver().getUsuario());
        repartoJsonObject.addProperty("fecha", repartoDf.format(reparto.getFecha()));
        repartoJsonObject.addProperty("matricula", reparto.getVehiculo().getMatricula());

        JsonArray detalleJsonArray = new JsonArray();

        for ( RepartoDetalle detalle : reparto.getDetalles() ) {

            JsonObject detalleObject = new JsonObject();

            detalleObject.addProperty("reparto_detalle_id", detalle.getId());
            detalleObject.addProperty("cliente", detalle.getCliente().getNombre());
            detalleObject.addProperty("comment_back", detalle.getCommentBack());
            detalleObject.addProperty("lat", detalle.getLatitude());
            detalleObject.addProperty("lng", detalle.getLongitude());
            detalleObject.addProperty("fotos", detalle.getFotos());
            detalleObject.addProperty("hora_descarga",
                                      detalle.getHoraDescarga() != null ? descargaDf.format(detalle.getHoraDescarga()) : "");
            detalleJsonArray.add(detalleObject);

        }

        finalObject.add("reparto", repartoJsonObject);
        finalObject.add("detalle", detalleJsonArray);
        finalArray.add(finalObject);
        return finalArray;
    }

}