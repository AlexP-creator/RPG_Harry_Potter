package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.characters.Wand;
import view.characters.Wizard;
import view.ennemies.Enemy;
import view.ennemies.EnemyList;
import view.items.SortingHat;
import view.spells.PlayerSpell;
import view.spells.Spell;

import java.io.File;
import java.util.Optional;


public class HelloApplication extends Application {

    private BorderPane root;
    private Label texte2;
    private Label texte1;

    private Label infos;
    private int enemyIndex = 0;
    private Image enemyImage;


    private ImageView imageViewDroite;


    @Override
    public void start(Stage primaryStage) {

        String playerName = askPlayerName();
        Wizard player = new Wizard(playerName);
        SortingHat.assignHouse(player);
        Wand wand = Wand.generateRandomWand();

        final Enemy[] enemy = {EnemyList.values()[enemyIndex].getEnemy()};

        final PlayerSpell[][] playerSpells = {{PlayerSpell.WINGARDIUM_LEVIOSA, PlayerSpell.ACCIO,
                PlayerSpell.EXPECTO_PATRONUM, PlayerSpell.SECTUMSEMPRA, PlayerSpell.AVADA_KEDAVRA,
                PlayerSpell.EXPELLIARMUS, PlayerSpell.GRYFFINDORSWORD}};

        // images perso/ennemies

        Image harryImage = new Image(getClass().getResource("/images/harry2.png").toString());
        ImageView imageViewGauche = new ImageView(harryImage);
        imageViewGauche.setFitWidth(300);
        imageViewGauche.setFitHeight(300);

        Image enemyImage = new Image(getClass().getResource("/images/Troll.png").toString());

        imageViewDroite = new ImageView(enemyImage);
        imageViewDroite.setFitWidth(300);
        imageViewDroite.setFitHeight(300);

// création bar points de vie
        Label playerHealthLabel = new Label("Points de vie : " + player.getHealth());
        ProgressBar playerHealthBar = new ProgressBar();
        playerHealthBar.setPrefWidth(200);
        playerHealthBar.setProgress((double) player.getHealth() / 100);

        Label enemyHealthLabel = new Label("Points de vie : " + enemy[0].getHealth());
        ProgressBar enemyHealthBar = new ProgressBar();
        enemyHealthBar.setPrefWidth(200);
        enemyHealthBar.setProgress((double) enemy[0].getHealth() / 100);

// Chiix des sorts

        Button attackButton = new Button("Attaquer");
        Button spellButton = new Button("Utiliser un sort");
        attackButton.setOnAction(event -> {

            attackEnemy(player, enemy[0], playerHealthLabel, playerHealthBar, enemyHealthLabel, enemyHealthBar);
            enemy[0].attack(player);
            playerHealthLabel.setText("Points de vie : " + player.getHealth());
            playerHealthBar.setProgress((double) player.getHealth() / 100);
            // Vérification de si le joueur est mort
            if (!player.isAlive()) {
                texte1.setText("Vous avez perdu !");
                return;
            }
        });

        VBox spellBox = new VBox();
        spellBox.setAlignment(Pos.CENTER);
        spellBox.setSpacing(10);
        for (PlayerSpell spell : playerSpells[0]) {
            final Enemy[] currentEnemy = {enemy[0]};

            Button spellButtonTemp = new Button(spell.toString());
            spellButtonTemp.setOnAction(event -> {
                Spell(player, currentEnemy[0], spell);

                if (!currentEnemy[0].isAlive()) {
                    enemyIndex++;
// Vérification de si il reste des ennemies
                    if (enemyIndex >= EnemyList.values().length) {
                        texte1.setText("Vous avez vaincu tous les ennemis !");
                        return;
                    }
                    currentEnemy[0] = EnemyList.values()[enemyIndex].getEnemy();
                    updateEnemyImage(enemyIndex);
                    enemyHealthLabel.setText("Points de vie : " + currentEnemy[0].getHealth());
                    enemyHealthBar.setProgress((double) currentEnemy[0].getHealth() / 100);

                }

                currentEnemy[0].attack(player);
                int damage = currentEnemy[0].getDamage();
                texte2.setText("Le " + currentEnemy[0].getName() + " vous inflige " + damage + " de dégâts.");

                playerHealthLabel.setText("Points de vie : " + player.getHealth());
                playerHealthBar.setProgress((double) player.getHealth() / 100);

                enemyHealthLabel.setText("Points de vie : " + currentEnemy[0].getHealth());
                enemyHealthBar.setProgress((double) currentEnemy[0].getHealth() / 100);

// Vérification de si le joueur est mort
                if (!player.isAlive()) {
                    return;
                }
            });
            spellBox.getChildren().add(spellButtonTemp);
        }
        spellButton.setOnAction(event -> {

            root.setCenter(spellBox);
        });
        root = new BorderPane();

        root.setPadding(new Insets(60));

        HBox boutonsMilieu = new HBox(spellButton);
        boutonsMilieu.setAlignment(Pos.CENTER);
        boutonsMilieu.setSpacing(10);
        root.setCenter(boutonsMilieu);

        HBox imageContainerGauche = new HBox(imageViewGauche);
        imageContainerGauche.setAlignment(Pos.CENTER_LEFT);
        root.setLeft(imageContainerGauche);

        VBox playerStats = new VBox(playerHealthLabel, playerHealthBar);
        playerStats.setAlignment(Pos.BOTTOM_LEFT);
        playerStats.setSpacing(1);

        VBox enemyStats = new VBox(enemyHealthLabel, enemyHealthBar);
        enemyStats.setAlignment(Pos.BOTTOM_RIGHT);
        enemyStats.setSpacing(1);

        HBox statsContainer = new HBox(playerStats, enemyStats);
        statsContainer.setAlignment(Pos.BOTTOM_CENTER);
        statsContainer.setSpacing(50);
        root.setBottom(statsContainer);

        HBox imageContainerDroite = new HBox(imageViewDroite);
        imageContainerDroite.setSpacing(100);
        root.setRight(imageContainerDroite);

        //Fond d'ecran
        Image backgroundImage = new Image(getClass().getResource("/images/img_1.png").toString());
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));

        VBox topContainer = new VBox();
        topContainer.setPadding(new Insets(50));
        topContainer.setStyle("-fx-font-size: 18px;");
        infos = new Label();
        texte2 = new Label();
        texte1 = new Label();

        topContainer.getChildren().addAll(infos,texte1, texte2);
        root.setTop(topContainer);
        infos.setText("Informations du joueur:\n\nNom : " + player.getName() + "\nMaison : " + player.getHouse().getName()+"\nVotre baguette est" + wand );

        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Harry Potter javafx");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void attackEnemy(Wizard player, Enemy enemy, Label playerHealthLabel, ProgressBar playerHealthBar,
                            Label enemyHealthLabel, ProgressBar enemyHealthBar) {

        playerHealthLabel.setText("Points de vie : " + player.getHealth());
        playerHealthBar.setProgress((double) player.getHealth() / 100);

    }

    public void Spell(Wizard wizard, Enemy enemy, PlayerSpell playerSpell) {
        Spell spell = new Spell();
        if (spell.isSuccess(wizard, playerSpell)) {

            int modifiedDamage = spell.getModifiedDamage(wizard, playerSpell);
            enemy.setHealth(enemy.getHealth() - modifiedDamage);
            texte1.setText("Vous avez lancé le sort " + playerSpell + " et infligé " + modifiedDamage + " de dégâts à l'ennemi.");
            if (enemy.getHealth() <= 0) {
                texte1.setText("L'ennemi a été vaincu !");
            }
        } else {
           texte1.setText("Le sort " + playerSpell + " a échoué.");
        }
    }

    private String askPlayerName() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nom du joueur");
        dialog.setHeaderText("Entrez votre nom de sorcier :");
        dialog.setContentText("Nom :");

        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    private void updateEnemyImage(int index) {
        String imagePath = "/images/" + EnemyList.values()[index].getEnemy().getName() + ".png";
        Image enemyImage = new Image(getClass().getResource(imagePath).toString());
        imageViewDroite.setImage(enemyImage);
    }

}

