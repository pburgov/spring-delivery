package com.pburgov.springdelivery.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.pburgov.springdelivery.config.ConfigProperties;
import com.pburgov.springdelivery.config.DateFormatter;
import com.pburgov.springdelivery.dao.entity.Reparto;
import com.pburgov.springdelivery.dao.serializer.RepartoSerializer;
import com.pburgov.springdelivery.dao.service.PictureService;
import com.pburgov.springdelivery.dao.service.RepartoService;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping( "/repartos" )
@SessionAttributes( "repartos" )
public class RepartoController {

    private RepartoService repartoService;
    private PictureService pictureService;
    private MessageSource messageSource;
    private ConfigProperties configProperties;

    private final Log logger = LogFactory.getLog(this.getClass());
    private static final LocalDate START_DATE = LocalDate.of(2020, 8, 1);
    private static final LocalDate END_DATE = LocalDate.of(2020, 8, 31);

    public RepartoController() {}

    @Autowired
    public RepartoController(
        RepartoService repartoService, PictureService pictureService, MessageSource messageSource, ConfigProperties configProperties ) {
        this.repartoService = repartoService;
        this.pictureService = pictureService;
        this.messageSource = messageSource;
        this.configProperties = configProperties;
    }

    @GetMapping( value = "/ver/{id}" )
    public String ver( @PathVariable( value = "id" ) Long id, Model model, RedirectAttributes flash, Locale locale ) {

        Reparto reparto = repartoService.fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(id);

        if ( reparto == null ) {
            flash.addFlashAttribute("error", messageSource.getMessage("text.reparto.flash.db.error", null, locale));
            return "redirect:/repartos/list";
        }
        model.addAttribute("titulo", "Detalle");
        model.addAttribute("reparto", reparto);
        return "repartos/ver";
    }

    @GetMapping( value = "/list" )
    public String listaPorFecha(
        @RequestParam( name = "paramDate", required = false ) Optional <String> fecha,
        RedirectAttributes flash, HttpSession session, Model model ) {
        List <Reparto> repartos = new ArrayList <>();
        java.sql.Date date = null;

        if ( !fecha.isPresent() ) {
            date = getFirstDayOfMonth();
            logger.info("Date is NOT present. New Date: " + date);
        } else if ( fecha.get().isEmpty() ) {
            flash.addFlashAttribute("error",
                                    messageSource.getMessage("text.repartos.list.flash.dates_not_selected.error", null, null));
            logger.info("Date is EMPTY");
            return "redirect:/repartos/list";
        } else {
            date = getSQLDate(fecha.get());
            if ( date.toLocalDate().isBefore(START_DATE) || date.toLocalDate().isAfter(END_DATE) ) {
                logger.info("Date out of Range");
                flash.addFlashAttribute("error",
                                        messageSource.getMessage("text.repartos.list.flash.dates_out_range.error", null, null));
                return "redirect:/repartos/list";
            }
        }

        repartos = repartoService.findByFecha(date);

        DateFormatter dateFormatter = new DateFormatter();
        String paramDate = dateFormatter.print(date, LocaleContextHolder.getLocale());

        if ( repartos.isEmpty() ) {
            logger.info("No Deliveries in this Date");
            flash.addFlashAttribute("error",
                                    messageSource.getMessage("text.repartos.list.flash.no_deliveries.error", new Object[]{
                                        paramDate}, null));
            return "redirect:/repartos/list";
        }

        model.addAttribute("paramDate", paramDate);
        model.addAttribute("repartos", repartos);
        session.setAttribute("repartos", repartos);
        return "repartos/list";
    }

    @RequestMapping( value = "/list/{id}", method = RequestMethod.GET )
    public String listaPorFecha(
        @PathVariable( value = "id", required = true ) Long id, HttpSession session,
        RedirectAttributes flash, Model model ) {
        if ( id != null ) {
            Reparto reparto = repartoService.fetchByIdWithVehiculoWithDriverWithDetallesWithCliente(id);
            model.addAttribute("jsonMap", getJSONObject(reparto));
            model.addAttribute("reparto", reparto);
        }

        return "repartos/fragment-detalle-reparto :: detallesReparto";
    }

    @ResponseBody
    @RequestMapping( value = "/fotos/{id}", method = RequestMethod.GET )
    public List <String> fotos( @PathVariable( value = "id" ) Long id, Model model ) {
//        List <String> pictures = pictureService.loadPictures(id);
        List <String> pictures = loadPicturesDemo().subList(0, id.intValue());
        model.addAttribute("pictures", pictures);

        return pictures;
    }

    public String getJSONObject( Reparto reparto ) {

        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls()
                                     .registerTypeAdapter(Reparto.class, new RepartoSerializer()).create();

        return gson.toJson(reparto);
    }

    private java.sql.Date getSQLDate( String fecha ) {
        java.util.Date utilDate = null;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            utilDate = format.parse(fecha);
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        return new java.sql.Date(utilDate.getTime());
    }

    private Date getFirstDayOfMonth() {
        LocalDate firstDate = LocalDate.of(2020, 8, 1);
        return Date.valueOf(firstDate);
    }

    private static List <String> loadPicturesDemo() {
        List <String> pictures = new ArrayList <>();
        pictures.add("Foto-1.jpg");
        pictures.add("Foto-2.jpg");
        pictures.add("Foto-3.jpg");
        pictures.add("Foto-4.jpg");
        pictures.add("Foto-5.jpg");
        pictures.add("Foto-6.jpg");
        pictures.add("Foto-7.jpg");
        pictures.add("Foto-8.jpg");
        pictures.add("Foto-9.jpg");
        pictures.add("Foto-10.jpg");
        return pictures;
    }

    private void getJSONObjectList( Reparto reparto ) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls()
                                     .registerTypeAdapter(Reparto.class, new RepartoSerializer()).create();
        try {
            gson.toJson(reparto, new FileWriter(configProperties.getTestJsonPath()));
        } catch ( JsonIOException | IOException e ) {

            e.printStackTrace();
        }
    }
}
