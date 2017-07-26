package br.com.kaike.assessement_fundamentos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Jogo jogo = new Jogo();
    private Cartas cartaSelecionada;
    private CardView cartaViewSelecionada;
    private CardView carta1, carta2, carta3;
    private CardView[] myCads;
    private Button confirma;
    private List<Cartas> jogoAtual;
    private MainActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carta1 = (CardView) findViewById(R.id.carta1);
        carta2 = (CardView) findViewById(R.id.carta2);
        carta3 = (CardView) findViewById(R.id.carta3);
        confirma = (Button) findViewById(R.id.botaoConfirmaId);
        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revelarCarta();
            }
        });
        myCads = new CardView[]{carta1, carta2, carta3};

        carta1.setOnClickListener(this);
        carta2.setOnClickListener(this);
        carta3.setOnClickListener(this);

        jogoAtual = jogo.embaralhar();
    }

    private void revelarCarta() {
//        Animation animRightIn = AnimationUtils.loadAnimation(this, R.anim.card_flip_right_in);
//        Animation animRightOut = AnimationUtils.loadAnimation(this, R.anim.card_flip_right_out);
//        Animation animLeftIn = AnimationUtils.loadAnimation(this, R.anim.card_flip_left_in);
//        Animation animLeftOut = AnimationUtils.loadAnimation(this, R.anim.card_flip_left_out);
//        StateListAnimator stateListAnimator =  new StateListAnimator();
//        cartaViewSelecionada.setAnimation(animRightIn);
        ((ImageView) cartaViewSelecionada.getChildAt(0)).setImageResource(cartaSelecionada == Cartas.AS ? R.drawable.as : R.drawable.coringa);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage(cartaSelecionada == Cartas.AS ? "Você ganhou, que sorte!" : "aha! Você Perdeu")
                        .setPositiveButton("Jogar mais", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                colocarCartas();
                                jogoAtual = jogo.embaralhar();
                            }
                        })
                        .setNegativeButton("Ir embora", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        }, 2500);
    }

    private void zerarJogo() {
        jogoAtual = jogo.embaralhar();
        colocarCartas();
    }

    @Override
    public void onClick(View v) {
        colocarCartas();
        cartaViewSelecionada = (CardView) v;
        ((CardView) v).setCardElevation(16);
        switch (v.getId()) {
            case R.id.carta1:
                cartaSelecionada = jogoAtual.get(0);
                break;
            case R.id.carta2:
                cartaSelecionada = jogoAtual.get(1);
                break;
            case R.id.carta3:
                cartaSelecionada = jogoAtual.get(2);
                break;
        }
    }

    private void colocarCartas() {
        for (CardView card : myCads) {
            card.setCardElevation(0);
            ((ImageView) card.getChildAt(0)).setImageResource(R.drawable.fundo);
        }
    }
}
