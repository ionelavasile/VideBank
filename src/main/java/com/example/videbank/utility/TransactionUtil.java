package com.example.videbank.utility;


import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.Balance;
import com.example.videbank.entity.PersistenceConstant;



/*
public class TransactionUtil {

    public static void checkTransactionValidity(
            TransactionDto transaction,
            Balance balanceByCurrency
    ){
        notNull.and(checkValidityOfCurrency).test(transaction.getCurrency())
                .setMessage(PersistenceConstant.INVALID_CURRENCY).throwIfInvalid();
        notNullBalance.test(balanceByCurrency)
                .setMessage(PersistenceConstant.INVALID_CURRENCY).throwIfInvalid();
        notNullDirectionOfTransaction.and(validDirectionOfTransaction)
                .test(transaction.getDirectionOfTransaction())
                .setMessage(PersistenceConstant.INVALID_DIRECTION).throwIfInvalid();
        notNull.test(transaction.getDescription())
                .setMessage(PersistenceConstant.DESCRIPTION_MISSING).throwIfInvalid();
        notNullAmount.and(invalidAmount).test(transaction.getAmount())
                .setMessage(PersistenceConstant.INVALID_AMOUNT).throwIfInvalid();
        insufficientFunds(transaction.getDirectionOfTransaction(), balanceByCurrency.getBalance()).test(transaction.getAmount())
                .setMessage(PersistenceConstant.INSUFFICIENT_FUNDS).throwIfInvalid();
    }
}
*/