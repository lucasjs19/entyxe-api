package com.entyxe.domain.validation;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.domain.enums.TipoCliente;
import com.entyxe.dto.request.ClienteRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentoValidator {
    ClienteRequest request;

    public Boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) return false;

        // rejeita CNPJs com todos os dígitos iguais
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso1[i];
            }

            int digito1 = soma % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso2[i];
            }

            int digito2 = soma % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;

            return digito1 == Character.getNumericValue(cnpj.charAt(12))
                    && digito2 == Character.getNumericValue(cnpj.charAt(13));

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validarCPF(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        // rejeita CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int digito1 = 11 - (soma % 11);
            digito1 = (digito1 >= 10) ? 0 : digito1;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int digito2 = 11 - (soma % 11);
            digito2 = (digito2 >= 10) ? 0 : digito2;

            return digito1 == Character.getNumericValue(cpf.charAt(9))
                    && digito2 == Character.getNumericValue(cpf.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validarFormatoDocumento(){
        if (request.getDocumento() == null || request.getDocumento().isBlank()){
            return  false;
        }

        return request.getDocumento().matches("\\d+");
    }

    public Boolean validarDocumento(){

        if (request.getTipoCliente() == TipoCliente.PF) {
            return validarCPF(request.getDocumento());
        } else if (request.getTipoCliente() == TipoCliente.PJ) {
            return validarCNPJ(request.getDocumento());
        } else {
            return false;
        }
    }
}
