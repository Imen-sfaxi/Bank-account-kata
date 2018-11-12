package org.societegenerale.fr.account.web;

import org.societegenerale.fr.account.entities.Account;
import org.societegenerale.fr.account.entities.Operation;
import org.societegenerale.fr.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/operations")
    public String index(){
        return "account";
    }

    @RequestMapping("/accountConsultation")
    public String consulter(Model model, String code,
                            @RequestParam(name="page", defaultValue="0") int page,
                            @RequestParam(name="size", defaultValue="10") int size){
        try{
            Account cp = accountService.accountConsultation(code);
            model.addAttribute("account", cp);
            Page<Operation> pageOperations= accountService.operationList(code, page, size);
            model.addAttribute("operationList", pageOperations.getContent());
            int[] pages = new int [pageOperations.getTotalPages()];
            model.addAttribute("pages",pages);
        } catch (Exception e) {
            model.addAttribute("exception",e);
        }
        return "account";
    }

    @RequestMapping(value="/saveOperation", method=RequestMethod.POST)
    public String saveOperation(Model model,
                                String operationType, String code,
                                double montant){

        try{
            if(operationType.equals("VERS")){
                accountService.makeDeposit(code, montant);
            }
            else if(operationType.equals("RETR")){
                accountService.makeWithdrawal(code, montant);
            }
        } catch(Exception e) {
            model.addAttribute("error", e);
            return "redirect:/accountConsultation?code="+code+
                    "&error="+e.getMessage();
        }

        return "redirect:/accountConsultation?code="+code;
    }

}
