import $ from "jquery";

export abstract class Modal {

    protected readonly modalID = this.modalContext.attr("id");

    protected constructor(protected readonly modalContext: JQuery<HTMLElement>) {

    }

    open() {
        this.modalContext.show();
    }

    close() {
        console.log("closing");
        this.modalContext.hide();
    }


    public abstract validate(): boolean
}