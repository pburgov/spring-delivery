package com.pburgov.springdelivery.controllers;

import com.pburgov.springdelivery.dao.entity.Cliente;
import com.pburgov.springdelivery.dao.service.ClienteService;
import com.pburgov.springdelivery.utils.paginator.PageRender;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes( "customers" )
@RequestMapping( "/customers" )
public class ClienteController {

    private ClienteService clienteService;

    private final Log logger = LogFactory.getLog(this.getClass());

    public ClienteController() {
    }

    @Autowired
    public ClienteController( ClienteService clienteService ) {
        this.clienteService = clienteService;
    }

    @RequestMapping( value = "/list", method = RequestMethod.GET )
    public String listAllCustomers(
        @RequestParam( name = "page", defaultValue = "0" ) int page,
        Model model,
        Authentication authentication,
        HttpServletRequest request ) {

        Pageable pageRequest = PageRequest.of(page, 10);

        Page <Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender <Cliente> pageRender = new PageRender <>("/customers/list", clientes);
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "customers/list";
    }

}
