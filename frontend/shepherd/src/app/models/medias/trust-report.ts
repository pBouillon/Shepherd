export class TrustReport {

    static DEFAULT: TrustReport = new TrustReport(50.);

    rate: number;

    constructor(rate: number) {
        this.rate = rate;
    }

}
