package c2j.listeners.operators

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait AssignmentOperatorListener extends BaseListenerTrait {
    @Override
    void enterAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

        appendIfNotNull ctx.Assign(), J.ASSIGN
        appendIfNotNull ctx.StarAssign(), J.MUL_ASSIGN
        appendIfNotNull ctx.DivAssign(), J.DIV_ASSIGN
        appendIfNotNull ctx.ModAssign(), J.MOD_ASSIGN
        appendIfNotNull ctx.PlusAssign(), J.ADD_ASSIGN
        appendIfNotNull ctx.MinusAssign(), J.SUB_ASSIGN
        appendIfNotNull ctx.LeftShiftAssign(), J.LSHIFT_ASSIGN
        appendIfNotNull ctx.RightShiftAssign(), J.RSHIFT_ASSIGN
        appendIfNotNull ctx.AndAssign(), J.AND_ASSIGN
        appendIfNotNull ctx.OrAssign(), J.OR_ASSIGN
        appendIfNotNull ctx.XorAssign(), J.XOR_ASSIGN
    }

    @Override
    void exitAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

    }
}