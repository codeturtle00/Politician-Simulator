package com.example.politicgame.Games.StampGame;

import java.util.ArrayList;
import java.util.List;

class ProposalsManager {
    /**
     * The list of proposals available to the user
     */
    private List<Proposal> proposals = new ArrayList<>();

    /**
     * The builder in charge of building proposals for the manager;
     */
    private ProposalBuilder proposalBuilder = new ConcreteProposalBuilder();

    /**
     * The list of nouns used to generate proposals
     */
    private List<Word> nouns;

    /**
     * The list of verbs used to generate proposals
     */
    private List<Word> verbs;


    /**
     * The list of starters to begin a prompt
     */
    private List<String> promptStarts;

    ProposalsManager(List<Word> nouns, List<Word> verbs, List<String> promptStarts){
        this.nouns = nouns;
        this.verbs = verbs;
        this.promptStarts = promptStarts;

        this.fillInProposals();
    }

    /**
     * Fill in the proposals List with all possible combinations of nouns and verbs, but with
     * random prompStarters;
     */
    private void fillInProposals(){
        for (Word verb: verbs){
            for (Word noun: nouns){
                String promptBegin = promptStarts.get((int) (Math.random() * (promptStarts.size())));
                Proposal proposal = constructProposal(verb, noun, promptBegin);
                proposals.add(proposal);
            }
        }
    }

    /**
     *
     * @param verb the verb used in the proposal
     * @param noun the noun used in the proposal
     * @param promptBegin the starter for the proposal
     * @return a proposal using verb, noun, and promptBegin
     */
    private Proposal constructProposal(Word verb, Word noun, String promptBegin){
        proposalBuilder.setNoun((Noun) noun);
        proposalBuilder.setVerb((Verb) verb);
        proposalBuilder.setPromptStart(promptBegin);
        proposalBuilder.setProposal();

        return proposalBuilder.getInstance();

    }

    /**
     * Return a list of proposals contain with number of proposals equal to size;
     * @param size the number of proposals contained in the list returned
     * @return a list of proposals
     *
     * precondition: 0 < size <= proposals.size()
     */
    List<Proposal> generateProposalList(int size){
        List<Proposal> result = new ArrayList<>();

        //this ensures that we can generate the maximum amount of proposals when the size given is out of range
        if (size > proposals.size() || size <= 0){
            size = proposals.size();
        }

        List<Proposal> proposalsCopy = new ArrayList<>(proposals);
        for (int i = 0; i < size; i++){
            Proposal proposal = proposalsCopy.get((int) (Math.random() * (proposalsCopy.size())));
            result.add(proposal);
            proposalsCopy.remove(proposal);
        }
        return result;
    }
}
