package c2j.listeners.operators

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait AssignmentOperatorListener extends BaseListenerTrait {
    @Override
    void enterAssignmentOperator(CParser.AssignmentOperatorContext ctx) {
        translateAndAppendIfNotNull(
                [
                        ctx.Assign(),
                        ctx.StarAssign(),
                        ctx.DivAssign(),
                        ctx.ModAssign(),
                        ctx.PlusAssign(),
                        ctx.MinusAssign(),
                        ctx.LeftShiftAssign(),
                        ctx.RightShiftAssign(),
                        ctx.AndAssign(),
                        ctx.OrAssign(),
                        ctx.XorAssign()
                ]
        )
    }
}