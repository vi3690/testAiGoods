package main.java.com.zxh;

public class auditServe {

    @ApiOperation("撤销申领")
    @DeleteMapping("/{diskId}/{invoiceType}/{applySn}")
    @CheckTerminal
    public ErrorDetails deleteClaim(Authentication authentication, @PathVariable String diskId, @PathVariable String invoiceType,
                                    @PathVariable String applySn) {
        Terminal terminal = deviceService.getDbDevice(diskId);
        return invoiceApplyService.deleteClaim(terminal, invoiceType, applySn);
    }

}
