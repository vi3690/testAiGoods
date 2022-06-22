package main.java.com.zxh;

public class auditServe {

    public ClaimDetail convert(StatusInfo.Sljgmx f) {
        ClaimDetail r = new ClaimDetail();
        r.setCode(f.fpdm);
        r.setQuantity(f.fs);
        r.setBeginNumber(f.qshm);
        r.setEndNumber(f.zzhm);
        return r;
    }

    @ApiOperation("撤销申领")
    @DeleteMapping("/{diskId}/{invoiceType}/{applySn}")
    @CheckTerminal
    public ErrorDetails deleteClaim(Authentication authentication, @PathVariable String diskId, @PathVariable String invoiceType,
                                    @PathVariable String applySn) {
        Terminal terminal = deviceService.getDbDevice(diskId);
        return invoiceApplyService.deleteClaim(terminal, invoiceType, applySn);
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
