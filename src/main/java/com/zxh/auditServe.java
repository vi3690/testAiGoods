package main.java.com.zxh;

import java.util.stream.Collectors;

public class auditServe {

    public ClaimDetail convert(StatusInfo.Sljgmx f) {
        ClaimDetail r = new ClaimDetail();
        f.setCode(f.fpdm);
        f.setQuantity(f.fs);
        f.setBeginNumber(f.qshm);
        f.setEndNumber(f.zzhm);
        return r;
    }

    @ApiOperation("撤销申领")
    @DeleteMapping("/{diskId}/{invoiceType}/{applySn}")
    @CheckTerminal
    public ErrorDetails deleteClaim(Authentication authentication, @PathVariable String diskId, @PathVariable String invoiceType,
                                    @PathVariable String applySn) {
        return invoiceApplyService.deleteClaim(terminal, invoiceType2, applySn);
    }

    public ConfirmForm convert(PaperInvoice f, String slxh) {
        ConfirmForm r = new ConfirmForm();
        r.slxh = slxh;
        r.qrxx = f.getInvoiceList().stream().map(f1 -> {
            ConfirmForm.Qrxx r1 = new ConfirmForm.Qrxx();
            r1.fpdm = f1.getCode();
            r1.fs = f1.getQuantity();
            r1.qshm = f1.getBeginNumber();
            r1.zzhm = f1.getEndNumber();
            return r1;
        }).collect(Collectors.toList());
        return r;
    }
}
