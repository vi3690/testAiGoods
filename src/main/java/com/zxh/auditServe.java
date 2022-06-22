package main.java.com.zxh;

import java.util.stream.Collectors;

public class auditServe {

    public ClaimDetail convert(StatusInfo.Sljgmx f) {
        ClaimDetail r = new ClaimDetail();
        r.setCode(f.fpdm);
        r.setQuantity(f.fs);
        r.setBeginNumber(f.qshm);
        r.setEndNumber(f.zzhm);
        return r;
    }
    public ApplyForm convert(Claim f) {
        ApplyForm r = new ApplyForm();
        r.fplxdm = f.getInvoiceType();
        r.fpzldm = f.getInvoiceKind();
        r.jbrxm = f.getOperator().getName();
        r.zjlx = Integer.parseInt(f.getOperator().getCredentialsName());
        r.zjhm = f.getOperator().getCredentialsCode();
        r.slfs = f.getReceiveMode();
        r.slsl = f.getQuantity();
        r.slsm = f.getExplain();
        r.psxx = new ApplyForm.Psxx();
        ClaimReceiver receiver = f.getReceiver();
        if (receiver != null) {
            r.psxx.bz = receiver.getRemark();
            r.psxx.gddh = receiver.getTelephone();
            r.psxx.yddh = receiver.getMobile();
            r.psxx.sjrdz = receiver.getAddress();
            r.psxx.sjrxm = receiver.getName();
            r.psxx.yb = receiver.getPostCode();
        }
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
    /**
     * 撤销申领
     * @param diskId 税控盘盘号
     * @param invoiceType 发票类型代码
     * @param applySn 申领编号
     * @return
     */
    @ApiOperation("撤销申领")
    @DeleteMapping("/{diskId}/{invoiceType}/{applySn}")
    public ErrorDetails deleteClaim(@PathVariable String diskId, @PathVariable String invoiceType,
                                    @PathVariable String applySn) {
        return claimApiService.deleteClaim(diskId, invoiceType, applySn);
    }
}
