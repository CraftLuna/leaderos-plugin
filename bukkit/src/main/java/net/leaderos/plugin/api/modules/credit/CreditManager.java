package net.leaderos.plugin.api.modules.credit;

import net.leaderos.shared.error.Error;
import net.leaderos.shared.helpers.RandomUtil;
import net.leaderos.shared.model.Response;
import net.leaderos.shared.modules.credit.CreditHelper;

import java.net.HttpURLConnection;
import java.util.Objects;

/**
 * CreditManager of leaderos-plugin credit module
 *
 * @author poyrazinan
 * @since 1.0
 */
public class CreditManager {

    /**
     * CreditManager Constructor
     */
    public CreditManager() {}

    /**
     * Gets player's credit
     * @param player to get credit
     * @return credit
     */
    public Double get(String player) {
        Response amount = CreditHelper.getRequest(player);
        if (amount == null || amount.getResponseMessage() == null) return null;

        return amount.getResponseMessage().getDouble("raw_credits");
    }

    /**
     * Sets player's credit
     * @param amount to set
     * @return true if success
     */
    public boolean createCoupon(String name, int amount) {
        Response response = CreditHelper.createCouponRequest(name, amount);

        return (Objects.requireNonNull(response).getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    /**
     * Sets player's credit
     * @param amount to set
     * @return true if success
     */
    public boolean createCoupon(int amount) {
        return createCoupon(RandomUtil.randomString(8), amount);
    }

    /**
     * Sets player's credit
     * @param amount the new bonus amount
     * @return true if success
     */
    public boolean setBonus(int amount) {
        Response setBonusResponse = CreditHelper.setBonusRequest(amount);

        return (Objects.requireNonNull(setBonusResponse).getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    /**
     * Sets player's credit
     * @param target to set credit
     * @param amount to set
     * @return true if success
     */
    public boolean set(String target, Double amount) {
        Response setCreditResponse = CreditHelper.setCreditRequest(target, amount);

        return (Objects.requireNonNull(setCreditResponse).getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    /**
     * Adds player's credit
     * @param target to add credit
     * @param amount to add
     * @return true if success
     */
    public boolean add(String target, Double amount) {
        Response addCreditResponse = CreditHelper.addCreditRequest(target, amount);

        return (Objects.requireNonNull(addCreditResponse).getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    /**
     * Removes player's credit
     * @param target to remove credit
     * @param amount to remove
     * @return true if success
     */
    public boolean remove(String  target, Double amount) {
        Response removeCreditResponse = CreditHelper.removeCreditRequest(target, amount);

        return (Objects.requireNonNull(removeCreditResponse).getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    /**
     * Sends player's credit
     * @param sender sender of credit
     * @param target receiver of credit
     * @param amount to send
     * @return null if success, error if not
     */
    public Error send(String sender, String target, Double amount) {
        Response sendCreditResponse = CreditHelper.sendCreditRequest(sender, target, amount);

        if ((Objects.requireNonNull(sendCreditResponse).getResponseCode() == HttpURLConnection.HTTP_OK && sendCreditResponse.getResponseMessage().getBoolean("status"))) {
            return null;
        }

        return sendCreditResponse.getError();
    }

}
