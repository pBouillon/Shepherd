import { TrustReport } from "./trust-report";

export class Media {

    static DESCRIPTION_MAX_LENGTH = 256;

    static DESCRIPTION_MIN_LENGTH = 16;

    static NAME_MIN_LENGTH = 3;

    static NAME_MAX_LENGTH = 32;

    constructor(
        public creationDate: Date,
        public description: string,
        public id: number,
        public name: string,
        public trustReport: TrustReport,
        public website: URL,
    ) { }

}
