package com.zrkworld.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkworld.cinema.pojo.Card;
import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.PageResult;
import com.zrkworld.cinema.service.CardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
@CrossOrigin
@RestController
public class CardController {
    @Resource
    CardService cardService;

    @RequestMapping("getCardData")
    public CinemaResult getCardData(String memberId, String pageIndex, String pageSize) {
        //这里使用分页插件pagehelper
        PageResult pageResult = new PageResult();
        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List lists = cardService.getCardData(memberId);
        PageInfo<Card> pageInfo = new PageInfo<>(lists);
        pageResult.setList(pageInfo.getList());
        pageResult.setPageTotal(pageInfo.getTotal());
        return CinemaResult.ok(pageResult);
    }

    /**
     * //注册会员卡，请求参数无，返回状态
     * export const registerCard = query=>{
     * return request({
     * url:"registerCard",
     * method:'get',
     * params:query
     * });
     * };
     */
    @RequestMapping("registerCard")
    public CinemaResult registerCard(String memberId) {

        cardService.registerCard(memberId);
        return CinemaResult.ok();
    }

    /**
     * //补卡，请求参数卡号id，返回新卡id号，并提示会员
     * export const reissueCard = query=>{
     * return request({
     * url:"reissueCard",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("reissueCard")
    public CinemaResult reissueCard(String cardId) {

        cardId = cardService.reissueCard(cardId);
        return CinemaResult.ok(cardId);
    }

    /**
     * //挂失会员卡，请求参数id，返回状态
     * export const loseCard = query=>{
     * return request({
     * url:"loseCard",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("loseCard")
    public CinemaResult loseCard(String cardId) {

        cardService.loseCard(cardId);
        return CinemaResult.ok();
    }

    /**
     * //解挂会员卡，请求参数卡号，返回状态
     * export const cancelCard = query=>{
     * return request({
     * url:"cancelCard",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("cancelCard")
    public CinemaResult cancelCard(String cardId) {

        cardService.cancelCard(cardId);
        return CinemaResult.ok();
    }

    /**
     * //充值会员卡，请求卡号、参数金额，返回状态
     * export const rechargeCard = query=>{
     * return request({
     * url:"rechargeCard",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("rechargeCard")
    public CinemaResult rechargeCard(String cardId, String value) {

        cardService.rechargeCard(cardId, Integer.parseInt(value));
        return CinemaResult.ok();
    }

    /**
     * //消费，请求参数卡号、金额、消费类型，返回状态
     * export const consumeCard = query=>{
     * return request({
     * url:"consumeCard",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("consumeCard")
    public CinemaResult consumeCard(String cardId, String price, String integral) {

        cardService.consumeCard(cardId, Integer.parseInt(price), Integer.parseInt(integral));
        return CinemaResult.ok();
    }

    /**
     * //积分兑换，请求参数：会员id，积分
     * //由于是总积分兑换，所以需要将消耗积分分散到所有卡上面
     * export const exchangeIntegral = query=>{
     * return request({
     * url:"exchangeIntegral",
     * method:'post',
     * params:query
     * });
     * };
     */
    @RequestMapping("exchangeIntegral")
    public CinemaResult exchangeIntegral(String memberId, String integral) {

        cardService.exchangeIntegral(memberId, Integer.parseInt(integral));
        return CinemaResult.ok();
    }
    /**
     *  //通过模糊条件查询到cardId，请求参数memberId,CardId，返回CardId列表
     * export const getCardIdByFuzzyQuery = query=>{
     *     return request({
     *         url:"getCardIdByFuzzyQuery",
     *         method:'post',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("getCardIdByFuzzyQuery")
    public CinemaResult getCardIdByFuzzyQuery(String memberId,String cardId) {


        return CinemaResult.ok(cardService.getCardIdByFuzzyQuery(memberId,cardId));
    }
    /**
     *  //通过完整cardid来查询card数据，返回Card对象
     * export const getCardByCardId = query=>{
     *     return request({
     *         url:"getCardByCardId",
     *         method:'post',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("getCardByCardId")
    public CinemaResult getCardByCardId(String cardId) {


        return CinemaResult.ok(cardService.getCardByCardId(cardId));
    }
}
