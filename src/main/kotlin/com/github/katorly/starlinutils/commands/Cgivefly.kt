package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.utils.Messager.sm
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.function.onlinePlayers
import taboolib.common5.util.replace

/**
 * 给予一名玩家飞行权限.
 *
 */
@CommandHeader("givefly")
object Cgivefly {
    @CommandBody
    val main = mainCommand {
        dynamic(optional = true) {
            suggestion<ProxyCommandSender> { sender, context ->
                onlinePlayers().map { it.name }
            }
            execute<ProxyCommandSender> { sender, context, arg ->
                val cmd: String? = conf["fly.give-fly"]?.replace(Pair("<player>", arg))
                sender.performCommand(cmd!!)
            }
        }
        execute<ProxyCommandSender> { sender, context, arg ->
            sm(sender, "${prefix}用法: /givefly <玩家ID>.")
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /givefly <玩家ID>.")
        }
    }
}