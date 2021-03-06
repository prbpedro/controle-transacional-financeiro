package com.github.prbpedro.ctf.util;

public class Constantes {

	public static final String ERROR_ACCOUNT_DOCUMENT_NUMBER_ALREDY_EXISTS = "Já existe account com este documentNumber";
	public static final String ERROR_PERSIST = "Erro ao persistir registro";
	public static final String SUCESS_PERSIST = "Sucesso ao incluir registro";
	public static final String ERROR_VALIDACAO = "Erro na validação da entrada";
	public static final String ERROR_VALIDACAO_JSON_INVALIDO = ERROR_VALIDACAO + ", json inválido";
	
	public static final String API_TAG_ACCOUNTS_CONTROLLER = "Account's Controller";
	public static final String API_DESCRIPTION_ACCOUNTS_CONTROLLER = "Mantém e retorna dados do tipo Account";
	public static final String API_MAPPING_ACCOUNTS_CONTROLLER = "/accounts";
	public static final String API_MAPPING_ACCOUNTS_GET = "/{id}";
	public static final String API_DESCRIPTION_OPERATION_SAVE_ACCOUNTS_CONTROLLER = "Cria novo registro do tipo Account";
	public static final String API_DESCRIPTION_OPERATION_GET_ACCOUNTS_CONTROLLER = "Retorna registro do tipo Account através do id";
	
	public static final String GLOBAL_MESSAGE = "globalMessage";
	
	public static final String CONTROLLERS_PACKAGE = "com.github.prbpedro.ctf.controllers";
	public static final String SWAGGER_API_TITLE = "API de Controle Transacional Financeiro";
	public static final String SWAGGER_API_DESCRIPTION = "API Rest que fornece um controle transacional financeiro simples";
	public static final String SWAGGER_API_VERSION = "1.0";
	
	public static final String OPERATION_TYPE_TABLE_NAME = "OperationsTypes";
	public static final String OPERATION_TYPE_COLUMN_ID_NAME = "OperationType_ID";
	public static final String OPERATION_TYPE_COLUMN_DESCRIPTION_NAME = "Description";
	public static final String COMPRA_A_VISTA = "COMPRA A VISTA";
	public static final String COMPRA_PARCELADA = "COMPRA PARCELADA";
	public static final String SAQUE = "SAQUE";
	public static final String PAGAMENTO = "PAGAMENTO";
	public static final Integer COMPRA_A_VISTA_ID = 1;
	public static final Integer COMPRA_PARCELADA_ID = 2;
	public static final Integer SAQUE_ID = 3;
	public static final Integer PAGAMENTO_ID = 4;
	
	
	public static final String ACCOUNT_TABLE_NAME = "Accounts";
	public static final String ACCOUNT_COLUMN_ID_NAME = "Account_ID";
	public static final String ACCOUNT_COLUMN_DOCUMENT_NUMBER_NAME = "Document_Number";
	
	public static final String QUERY_EXIST_ACCOUNT_BY_DOCUMENT_NUMBER = "SELECT COUNT(e)>0 FROM Account e WHERE e.documentNumber=?1";
	
	public static final String ERRO_BUSCA_ACCOUNT = "Erro na busca de account";
	public static final String SUCESSO_BUSCA_ACCOUNT = "Account encontrada";
	public static final String NOT_FOUND_BUSCA_ACCOUNT = "Account não encontrada";
	
	public static final String TRANSACTION_TABLE_NAME = "Transactions";
	public static final String TRANSACTION_COLUMN_ID_NAME = "Transaction_ID";
	public static final String TRANSACTION_COLUMN_ACCOUNT_ID_NAME = "Account_ID";
	public static final String TRANSACTION_COLUMN_OPERATION_ID_NAME = "OperationType_ID";
	public static final String TRANSACTION_COLUMN_AMOUNT_NAME = "Amount";
	public static final String TRANSACTION_COLUMN_AVAIBLE_CREDIT_LIMIT_NAME = "AvailableCreditLimit";
	public static final String TRANSACTION_COLUMN_EVENT_DATE_NAME = "EventDate";
	
	public static final String API_TAG_TRANSACTIONS_CONTROLLER = "Transaction's Controller";
	public static final String API_DESCRIPTION_TRANSACTIONS_CONTROLLER = "Mantém dados do tipo Transaction";
	public static final String API_MAPPING_TRANSACTIONS_CONTROLLER = "/transactions";
	public static final String API_DESCRIPTION_OPERATION_SAVE_TRANSACTIONS_CONTROLLER = "Cria novo registro do tipo Transaction";
	
	public static final String ACCOUNT_ID_INVALIDO = "accountId inválido";
	public static final String AMOUNT_INVALIDO = "amount inválido";
	public static final String OPERATION_TYPE_ID_INVALIDO = "operationTypeId inválido";
	public static final String NAO_EXISTE_CREDITO_SUFICIENTE = "não existe crédito suficiente.";
	public static final String AVAIBLE_CREDIT_LIMIT = "avaibleCreditLimit";
	
}