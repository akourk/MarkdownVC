//TODO: Re-organize architecture?
// - make classes private
// separate into different files
// make prompts? no don't do that. will be direct input from front-end anyway.
// prompts will only help with testing, but not necessary.
// build out read instructions from file?

class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        String title = "editable markdown file";

        String filename = HandleMD.generateFileNameFromTitle(title);

        HandleMD.deleteMDFile(filename);

        if (HandleMD.checkExists(filename)) {
            HandleMD.printMDContent(filename);
            // handleMD.OpenMD(filename);
        } else {
            HandleMD.generateMDFile(filename);
            WriteToMD.setInitializeFlow(filename, title);


        }
        
        // handleMD.DeleteMDFile(filename);
    }
}