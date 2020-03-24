export abstract class Modal {

    protected readonly modalID = this.modalContext.attr("id");

    protected constructor(protected readonly modalContext: JQuery<HTMLElement>) {

    }


    open() {
        this.modalContext.show();
        this.focus();
    }

    close() {
        this.modalContext.hide();
    }

    protected abstract focus(): void;
}