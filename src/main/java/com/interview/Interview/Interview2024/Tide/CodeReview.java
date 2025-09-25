package com.interview.Interview.Interview2024.Tide;

/*
code review below :

Adding a new version of the basket checkout that stops the user from checking out if they don't have available
balance in their wallet. The authorisation is controlled using a parameter so we can use it internally in service
to service calls. Calls the wallet-service over HTTP to get the balance and then to pay.

*/

public class CodeReview {
/*
    @PutMapping(value = "/new/{basketId}/v2/checkout/confirm")
    public String checkoutBasket(@PathVariable String basketId,
                                 @RequestParam boolean isInternal,
                                 Authentication auth,
                                 @RequestParam String walletId) {


        boolean canAccess = true;
        if (!isInternal || auth.getPrincipal().getName() != null) {
            List <Basket> bList = basketRepository.findByUsername(auth.getPrincipal().getName());

            boolean ownsBasket = false;
            bList.stream().forEach(b -> {
                if (b.getId() == basketId) {
                    ownsBasket = true;
                }
            });
            if (!ownsBasket) canAccess = false;
        }

        if (!canAccess) return "NOT-ALLOWED";

        if (walletId == null) {
            var basketOptional = basketRepository.findById(basketId);
            walletId = basketOptional.get().getUserWallet();
        }

        Wallet wallet = walletService.getWallet(walletId);
        Optional.ofNullable(wallet).orElseThrow();
        double balance = wallet.getBalance();

        var basketOptional = basketRepository.findById(basketId);
        if (balance > basketOptional.get().getTotal()) {
            basketRepository.changeStatus(basketId, "SEND_FOR_DELIVERY");
            walletService.reduceBalance(walletId, basketOptional.get().getTotal());
        } else {
            return "INSUFFICIENT-FUNDS";
        }

        return "OK";
    }

 */
}
