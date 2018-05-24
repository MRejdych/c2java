package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait AdditiveExpressionListener extends BaseListenerTrait {
    @Override
    void enterAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
    }

    @Override
    void exitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {

    }
}