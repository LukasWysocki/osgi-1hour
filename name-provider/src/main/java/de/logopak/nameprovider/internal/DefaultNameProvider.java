package de.logopak.nameprovider.internal;

import de.logopak.nameprovider.NameProvider;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DefaultNameProvider implements NameProvider {

  private Random random = new Random();

  private List<String> names = Arrays
      .asList("Aragorn", "Arwen", "Bilbo Baggins", "Frodo Baggins", "Barliman Butterbur", "Beregond and Bergil", "Fredegar Bolger",
          "Tom Bombadil", "Boromir", "Meriadoc Brandybuck", "Celeborn", "Ceorl (Middle-earth)", "Círdan", "Déagol", "Denethor", "Dúnhere",
          "Eärnil II", "Elfhelm", "Elladan and Elrohir", "Elrond", "Éomer", "Éowyn", "Erkenbrand", "Faramir", "Forlong the Fat",
          "Galadriel", "Samwise Gamgee", "Gamling", "Gandalf", "Ghân-buri-Ghân", "Gildor Inglorion", "Gimli (Middle-earth)", "Glorfindel",
          "Goldberry", "Gollum", "Gothmog (Third Age)", "Gríma Wormtongue", "Grimbold", "Háma (Middle-earth)", "Húrin the Tall", "Isildur",
          "Legolas", "Farmer Maggot", "Mouth of Sauron", "Nazgûl", "Old Man Willow",
          "List of original characters in The Lord of the Rings film series", "Radagast", "Saruman", "Sauron", "Shelob", "Théoden",
          "Théodred", "Peregrin Took", "Tower Guard of Gondor", "Treebeard", "Witch-king of Angmar");

  public String provideName() {
    return names.get(random.nextInt(names.size()));
  }
}
