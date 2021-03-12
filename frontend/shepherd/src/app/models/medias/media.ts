
export class Media {

    description: string;

    name: string;

    constructor(name: string, description?: string) {
        this.name = name;
        this.description = description || name;
    }

}
