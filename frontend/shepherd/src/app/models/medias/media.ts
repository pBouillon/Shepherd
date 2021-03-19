import { TrustReport } from "./trust-report";

export class Media {

    creationDate: Date = new Date();

    description: string;

    id: number = 0;

    name: string;

    trustReport: TrustReport = new TrustReport();

    website?: URL;

    constructor(name: string, description?: string) {
        this.name = name;
        this.description = description || name;
    }

}
