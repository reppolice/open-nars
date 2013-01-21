package com.googlecode.opennars.parser.loan.Loan.Absyn; // Java Package generated by the BNF Converter.

public class LitSVarD extends Literal {
  public final String ident_;
  public final ListIdent listident_;

  public LitSVarD(String p1, ListIdent p2) { ident_ = p1; listident_ = p2; }

  public <R,A> R accept(com.googlecode.opennars.parser.loan.Loan.Absyn.Literal.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof com.googlecode.opennars.parser.loan.Loan.Absyn.LitSVarD) {
      com.googlecode.opennars.parser.loan.Loan.Absyn.LitSVarD x = (com.googlecode.opennars.parser.loan.Loan.Absyn.LitSVarD)o;
      return this.ident_.equals(x.ident_) && this.listident_.equals(x.listident_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.ident_.hashCode())+this.listident_.hashCode();
  }


}