package com.pburgov.springdelivery.controllers;

import com.pburgov.springdelivery.config.DateFormatter;
import com.pburgov.springdelivery.dao.entity.Reparto;
import com.pburgov.springdelivery.dao.service.RepartoService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final Log logger = LogFactory.getLog(this.getClass());

    private RepartoService repartoService;
    private MessageSource messageSource;

    public PedidoController() {
    }

    @Autowired
    public PedidoController(RepartoService repartoService, MessageSource messageSource) {
        this.repartoService = repartoService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/list")
    public String listaPorFecha(@RequestParam(name = "paramDate", required = false) Optional<String> fecha,
                                RedirectAttributes flash, HttpSession session, Model model) {
        Date date = null;
        DateFormatter dateFormatter = new DateFormatter();
        if (!fecha.isPresent()) {
            date = java.sql.Date.valueOf(LocalDate.now());
            logger.info("Fecha is NOT present. New Fecha: " + date);

        } else {
            logger.info("fecha.get(): " + fecha.get());
            try {
                date = dateFormatter.parse(fecha.get(), LocaleContextHolder.getLocale());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<Reparto> repartos = new ArrayList<>();
        repartos = repartoService.findByFecha(date);
        if (repartos.isEmpty()) {
            logger.info("Pedidos: vacio");
            flash.addFlashAttribute("error", messageSource.getMessage("text.pedidos.list.flash.db.error", null, null));
            return "pedidos/list";
        }

        logger.info("Fecha + dateForamtter: " + dateFormatter.print(date, LocaleContextHolder.getLocale()));
        model.addAttribute("titulo", "Listado de Repartos");
        model.addAttribute("paramDate", dateFormatter.print(date, LocaleContextHolder.getLocale()));
        model.addAttribute("repartos", repartos);
        session.setAttribute("repartos", repartos);
        return "pedidos/list";
    }
}

