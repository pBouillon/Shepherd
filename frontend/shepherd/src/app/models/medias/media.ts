import { TrustReport } from "./trust-report";

export class Media {

    constructor(
        public creationDate: Date,
        public description: string,
        public id: number,
        public name: string,
        public trustReport: TrustReport,
        public website: URL,
    ) { }

}
