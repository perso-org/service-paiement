package com.tfa.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.tfa.dto.EvenementCommande;
import com.tfa.producer.PaiementProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class Paiementconsumer {

	private final PaiementProducer producer;
	@RabbitListener(queues = "queue-rl-cl-001")
	public void remboursement(EvenementCommande commande) {
		log.info("Traiement de remboursement commande {}",commande.getCommande().getIdCmd());
		commande.setMessage("Remboursement sera effectif dans 48 heures!");
		commande.setStatus("Remboursement en cours...");
		producer.traiterRemboursement(commande);
	}
}
