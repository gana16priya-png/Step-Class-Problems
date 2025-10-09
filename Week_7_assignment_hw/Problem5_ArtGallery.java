class ArtPiece {
    protected String title;
    protected String artist;

    public ArtPiece(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Artist: " + artist);
    }
}

// Painting class
class Painting extends ArtPiece {
    public Painting(String title, String artist) {
        super(title, artist);
    }

    public void showBrushTechnique(String technique) {
        System.out.println(title + " uses brush technique: " + technique);
    }

    public void showColorPalette(String palette) {
        System.out.println(title + " color palette: " + palette);
    }

    public void showFrameSpec(String spec) {
        System.out.println(title + " frame specification: " + spec);
    }
}

// Sculpture class
class Sculpture extends ArtPiece {
    public Sculpture(String title, String artist) {
        super(title, artist);
    }

    public void showMaterial(String material) {
        System.out.println(title + " made of: " + material);
    }

    public void showDimensions(String dimensions) {
        System.out.println(title + " dimensions: " + dimensions);
    }

    public void showLighting(String lighting) {
        System.out.println(title + " requires lighting: " + lighting);
    }
}

// Digital Art class
class DigitalArt extends ArtPiece {
    public DigitalArt(String title, String artist) {
        super(title, artist);
    }

    public void showResolution(String resolution) {
        System.out.println(title + " resolution: " + resolution);
    }

    public void showFileFormat(String format) {
        System.out.println(title + " file format: " + format);
    }

    public void showInteractiveElement(String element) {
        System.out.println(title + " interactive element: " + element);
    }
}

// Photography class
class Photography extends ArtPiece {
    public Photography(String title, String artist) {
        super(title, artist);
    }

    public void showCameraSettings(String settings) {
        System.out.println(title + " camera settings: " + settings);
    }

    public void showEditingDetails(String details) {
        System.out.println(title + " editing details: " + details);
    }

    public void showPrintSpec(String spec) {
        System.out.println(title + " print specification: " + spec);
    }
}

// Main program
public class Problem5_ArtGallery {
    public static void main(String[] args) {
        // Mixed collection of artworks
        ArtPiece[] gallery = {
            new Painting("Starry Night", "Van Gogh"),
            new Sculpture("David", "Michelangelo"),
            new DigitalArt("Virtual Landscape", "Alice Chen"),
            new Photography("Moonrise", "Ansel Adams")
        };

        // Downcasting to access specific details
        for (ArtPiece art : gallery) {
            art.displayInfo();

            if (art instanceof Painting) {
                Painting painting = (Painting) art;
                painting.showBrushTechnique("Impasto");
                painting.showColorPalette("Blue & Yellow");
                painting.showFrameSpec("Golden Frame");
            } else if (art instanceof Sculpture) {
                Sculpture sculpture = (Sculpture) art;
                sculpture.showMaterial("Marble");
                sculpture.showDimensions("5m tall");
                sculpture.showLighting("Soft spotlight");
            } else if (art instanceof DigitalArt) {
                DigitalArt digital = (DigitalArt) art;
                digital.showResolution("4K");
                digital.showFileFormat("PNG");
                digital.showInteractiveElement("Touch-enabled zoom");
            } else if (art instanceof Photography) {
                Photography photo = (Photography) art;
                photo.showCameraSettings("f/11, 1/250s, ISO 100");
                photo.showEditingDetails("Black-and-white contrast enhanced");
                photo.showPrintSpec("30x40 inch matte print");
            }

            System.out.println();
        }
    }
}
