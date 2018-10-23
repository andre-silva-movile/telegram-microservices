package message.core

import spock.lang.Specification

class BotSpecification extends Specification {

    def "do system queue sink"(){
        setup:
        def messageDestination = Mock(MessageChannel.class)
        messageDestination.send(_) >> true
        def channelResolver = Mock(BinderAwareChannelResolver.class)
        channelResolver.resolveDestination(_) >> messageDestination
        def routerSink = new RouterSink(routerConfiguration, channelResolver)

        expect:
        routerSink.listen(input) == output

        where:
        input                                                                               |  output
        ProtobufUtils.serialize(MessageWrapperCreator.pendingRoute(), MessageWrapper.class) |  true
        ProtobufUtils.serialize(message.processingEmptyRoute(), MessageWrapper.class)       |  true
        ProtobufUtils.serialize(message.processingEnricherRoute(), MessageWrapper.class)    |  true
        ProtobufUtils.serialize(message.processingEmptySystemRoute(), MessageWrapper.class) |  true
        ProtobufUtils.serialize(message.processingSuccessRoute(), MessageWrapper.class)     |  true
        ProtobufUtils.serialize(message.successRoute(), MessageWrapper.class)               |  true
        ProtobufUtils.serialize(message.processingInvalidRoute(), MessageWrapper.class)     |  true
        ProtobufUtils.serialize(message.processingNullInvalidRoute(), MessageWrapper.class) |  true
        ProtobufUtils.serialize(message.processingNullPendingRoute(), MessageWrapper.class) |  true
    }
}
